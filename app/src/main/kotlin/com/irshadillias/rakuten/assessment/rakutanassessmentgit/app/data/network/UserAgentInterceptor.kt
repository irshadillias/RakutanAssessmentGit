package com.irshadillias.rakuten.assessment.rakutanassessmentgit.app.data.network

import com.irshadillias.rakuten.assessment.rakutanassessmentgit.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/*
 * Adds a User-Agent header to the request. The header follows this format:
 * <AppName>/<version> Dalvik/<version> (Linux; U; Android <android version>; <device ID> Build/<buildtag>)
 *
 * Useful links:
 * User agents in mobile apps: https://www.scientiamobile.com/correctly-form-user-agents-for-mobile-apps
 * Test user agent: https://faisalman.github.io/ua-parser-js/
 */
class UserAgentInterceptor : Interceptor {
    private val userAgent = "showcase/${BuildConfig.VERSION_NAME} ${System.getProperty("http.agent")}"

    override fun intercept(chain: Interceptor.Chain): Response = chain
        .request()
        .newBuilder()
        .header("User-Agent", userAgent)
        .build()
        .let { chain.proceed(it) }
}
