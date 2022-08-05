package com.warlock.Config


import com.warlock.JwtAuthenticationEntryPoint
import com.warlock.JwtAuthenticationTokenFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import javax.servlet.http.HttpServletRequest

@Configuration
@EnableWebSecurity
open class Securityconfig : WebSecurityConfigurerAdapter() {

    @Autowired
    val unauthorizedHandler: JwtAuthenticationEntryPoint? = null


    @Bean
    @Throws
    open fun jwtAuthenticationTokenFilterBean() : JwtAuthenticationTokenFilter {
        return JwtAuthenticationTokenFilter()
    }



    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .cors().and()
            .authorizeRequests()
            .antMatchers(
                "/",
                "/*.html",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js",
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/v2/api-docs",
                "/webjars/**",
                "/auth/**"
            ).permitAll()
            .anyRequest()
            .authenticated()
        http.addFilterBefore(jwtAuthenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter::class.java)
        http.headers().cacheControl()
        http.cors().configurationSource { request: HttpServletRequest? -> CorsConfiguration().apply {
            this.allowedMethods = arrayListOf("POST","PUT","GET","DELETE")
            this.allowedOrigins = arrayListOf("*")
            this.allowedHeaders = arrayListOf("*")
            this.exposedHeaders = arrayListOf("*")
        } }
    }
}