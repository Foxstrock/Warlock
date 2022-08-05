package com.warlock

import com.fasterxml.jackson.core.JsonProcessingException
import com.warlock.Login.UserLogin
import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.*
import kotlin.jvm.Throws


@Component
class JwtTokenUtil : Serializable {
    @Value("\${jwt.expiration}")
    val expiration: Long? = null

    @Value("\${jwt.secret}")
    val secret: String? = null

    companion object {
        private const val serialVersionUID = -3301605591108950415L
        const val CLAIM_KEY_USERNAME = "sub"
        const val CLAIM_KEY_ID = "ID"
        const val CLAIM_KEY_CREATED = "iat"
        const val CLAIM_KEY_AUTHORITIES = "role"
        const val CLAIM_KEY_AUTH = "auth"
        const val CLAIM_KEY_AUDIENCE = "audience"
        const val CLAIM_KEY_APPLICATION_NAME = "nam"
        const val CLAIM_KEY_UUIDPH = "uuidPh"
        const val CLAIM_KEY_ROLE = "role"
    }

    @Throws(JsonProcessingException::class)
    fun generateToken(user: UserLogin): String {
        val claims : MutableMap<String, Any?> = HashMap()
        claims[CLAIM_KEY_USERNAME] = user.email
        claims[CLAIM_KEY_AUTHORITIES] = user.role
        claims[CLAIM_KEY_ID] = user.id
        claims[CLAIM_KEY_CREATED] = Date()
        return Jwts.builder()
            .setClaims(claims)
            .setExpiration(generateExpirationDate())
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }


    @Throws(JsonProcessingException::class)
    fun generateRefreshToken(user: UserLogin): String {
        val encryptedMail = BCryptPasswordEncoder().encode(user.email)
        val claims : MutableMap<String, Any?> = HashMap()
        claims[CLAIM_KEY_USERNAME] = encryptedMail
        claims[CLAIM_KEY_ID] = user.id
        claims[CLAIM_KEY_CREATED] = Date()
        claims[CLAIM_KEY_AUTHORITIES]= user.role
        return  Jwts.builder()
            .setClaims(claims)
            .setExpiration(generateRefreshExpirationDate())
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun getClaimsFromToken(token: String): Claims? {
        return try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
        } catch (ex: SignatureException) {
            throw BadCredentialsException("INVALID_CREDENTIALS", ex)
        } catch (ex: MalformedJwtException) {
            throw BadCredentialsException("INVALID_CREDENTIALS", ex)
        } catch (ex: UnsupportedJwtException) {
            throw BadCredentialsException("INVALID_CREDENTIALS", ex)
        } catch (ex: IllegalArgumentException) {
            throw BadCredentialsException("INVALID_CREDENTIALS", ex)
        } catch (ex: ExpiredJwtException) {
            throw ex
        }
    }

    private fun generateExpirationDate(): Date {
        return Date(System.currentTimeMillis()  + 999999999999999999)
    }

    private fun generateRefreshExpirationDate(): Date {
        return Date(System.currentTimeMillis() + 999999999999999999)
    }

    fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration!!.before(Date())
    }

    fun validateToken(token: String, userDetails: UserLogin): Boolean {
        val username = getEmailFromToken(token)
        if (userDetails.email.equals(username) && !isTokenExpired(token)){
            return true
        }
        return false
    }

    fun getEmailFromToken(token: String): String? {
        return try {
            val claims = getClaimsFromToken(token)
            claims!![CLAIM_KEY_USERNAME] as String ?
        } catch (e: Exception) {
            null
        }
    }

    fun getuuidPhFromToken(token: String): String? {
        return try {
            val claims = getClaimsFromToken(token)
            claims!![CLAIM_KEY_UUIDPH] as String ?
        } catch (e: Exception) {
            null
        }
    }

    fun getUserIdFromToken(token: String): String? {
        return try {
            val claims = getClaimsFromToken(token)
            claims!![CLAIM_KEY_ID].toString()
        } catch (e: Exception) {
            null
        }
    }

    fun getCreatedDateFromToken(token: String): Date? {
        return try {
            val claims = getClaimsFromToken(token)
            Date((claims!![CLAIM_KEY_CREATED] as Long?)!!)
        } catch (e: Exception) {
            null
        }
    }

    fun getExpirationDateFromToken(token: String): Date? {
        return try {
            val claims = getClaimsFromToken(token)
            claims!!.expiration
        } catch (e: Exception) {
            throw e
        }
    }

    fun getRoleFromToken(token: String): String? {
        return try {
            val claims = getClaimsFromToken(token)
            claims!![CLAIM_KEY_AUTHORITIES] as String?
        } catch (e: Exception) {
            null
        }
    }

    fun getSystemDeviceFromToken(token: String): String? {
        return try {
            val claims = getClaimsFromToken(token)
            claims!![CLAIM_KEY_AUDIENCE] as String?
        } catch (e: Exception) {
            null
        }
    }
    //-----------------------------------------Method for Communication between BE----------------------------------------//
    @Throws(JsonProcessingException::class)
    fun generateCommunicationToken(auth : String, applicationName : String): String {
        val encryptedAuth = BCryptPasswordEncoder().encode(auth)
        val claims: MutableMap<String, Any?> = HashMap()
        claims[CLAIM_KEY_AUTH] = encryptedAuth
        claims[CLAIM_KEY_CREATED] = Date()
        return Jwts.builder()
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun validateCommunicationToken(token: String, auth: String): Boolean {
        return BCryptPasswordEncoder().matches( auth, getAuthFromToken(token))
    }

    fun getAuthFromToken(token: String): String? {
        return try {
            val claims = getClaimsFromToken(token)
            claims!![CLAIM_KEY_AUTH] as String ?
        } catch (e: Exception) {
            null
        }
    }

    fun getApplicationNameFromToken(token: String): String? {
        return try {
            val claims = getClaimsFromToken(token)
            claims!![CLAIM_KEY_APPLICATION_NAME] as String ?
        } catch (e: Exception) {
            null
        }
    }



}