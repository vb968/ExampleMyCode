package com.b2b.rqst.network

import okhttp3.OkHttpClient
import java.security.*
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*

object HttpsTrustManager : X509TrustManager {
    private var finalTm: X509TrustManager? = null

    init {
        var tmf: TrustManagerFactory? = null
        try {
            tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        try {
            tmf!!.init(null as KeyStore?)
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        }
        for (tm in tmf!!.trustManagers) {
            if (tm is X509TrustManager) {
                finalTm = tm
                break
            }
        }
        checkNotNull(finalTm) { "No X509 TrustManager available. Unable to check certificate chain" }

    }

    @Throws(CertificateException::class) override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
        finalTm!!.checkClientTrusted(chain, authType)
    }

    @Throws(CertificateException::class) override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
        requireNotNull(chain) { "checkServerTrusted: X509Certificate array is null" }
        require(chain.size != 0) { "checkServerTrusted: X509Certificate is empty" }
        finalTm!!.checkServerTrusted(chain, authType)
    }

    override fun getAcceptedIssuers(): Array<X509Certificate> {
        return finalTm!!.acceptedIssuers
    }

    private val trustManagers: Array<TrustManager> = arrayOf(HttpsTrustManager)
    fun getSSLSocketFactory(protocol: String): SSLSocketFactory {
        var sslContext: SSLContext? = null
        try {
            sslContext = SSLContext.getInstance(protocol)
            sslContext.init(null, trustManagers, SecureRandom())
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        }
        return sslContext!!.socketFactory
    }

    fun getOkHttpClient(protocol: String): OkHttpClient.Builder {
        val sslSocketFactory = getSSLSocketFactory(protocol)
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslSocketFactory, trustManagers[0] as X509TrustManager)
        builder.hostnameVerifier(HostnameVerifier { hostname, session -> true })
        return builder
    }

}