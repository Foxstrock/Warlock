package com.warlock



import com.warlock.Login.UserLogin
import com.warlock.Login.UserLoginInterface
import com.warlock.Login.UserRoles
import com.warlock.warlock.Utility.SingletoneData.tokenHeader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.jvm.Throws

class JwtAuthenticationTokenFilter : OncePerRequestFilter() {
    @Autowired
    var jwtTokenUtil: JwtTokenUtil? = null

    @Autowired
    lateinit var userLogin : UserLoginInterface


    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        //---------filter for communication between BE---------//
        val commsToken = request.getHeader(tokenHeader)
        if (commsToken != null) {
                    var user = userLogin.findByEmail(jwtTokenUtil!!.getEmailFromToken(commsToken)!!).get()
                    if (SecurityContextHolder.getContext().authentication == null) {
                        if (jwtTokenUtil!!.validateToken(commsToken , user)) {
                            val beDetails = jwtTokenUtil!!.getApplicationNameFromToken(commsToken)
                            val authentication = UsernamePasswordAuthenticationToken(beDetails, null, null)
                            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                            SecurityContextHolder.getContext().authentication = authentication
                        }

            }

        }
        //-----------------------------------------------------//
        chain.doFilter(request, response)
    }

    fun getAuthorities(user: Any): MutableCollection<out GrantedAuthority> {
        val authorities = ArrayList<SimpleGrantedAuthority>()
        when(user){
            is UserLogin ->{authorities.add(SimpleGrantedAuthority(UserRoles.ADMIN.name))}
            else -> {throw Exception("Wrong Role")}
        }
        return authorities
    }

}