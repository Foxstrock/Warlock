package com.warlock.Config


import com.warlock.warlock.Utility.SingletoneData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class WarlockConfig {


    @Value("\${jwt.header}")
    val tokenHeader: String = ""
    @Value("\${jwt.refresh}")
    val refreshToken: String = ""



    @Bean
    open fun init(){

        SingletoneData.tokenHeader = tokenHeader
        SingletoneData.tokenRefreshHeader = refreshToken
    }





    }
