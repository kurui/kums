package com.kurui.kums.base.ssl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MyTrustManager
	implements TrustManager, X509TrustManager
{

	public MyTrustManager()
	{
	}

	public X509Certificate[] getAcceptedIssuers()
	{
		return null;
	}

	public boolean isServerTrusted(X509Certificate certs[])
	{
		return true;
	}

	public boolean isClientTrusted(X509Certificate certs[])
	{
		return true;
	}

	public void checkServerTrusted(X509Certificate ax509certificate[], String s)
		throws CertificateException
	{
	}

	public void checkClientTrusted(X509Certificate ax509certificate[], String s)
		throws CertificateException
	{
	}
}
