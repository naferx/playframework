/*
 * Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com>
 */
package play.api.libs.ws.ssl

import org.specs2.mutable._
import org.specs2.mock.Mockito

class KeyStoreSpec extends Specification with Mockito {

  "StringBasedKeyStoreBuilder" should {

    "create several certificate" in {
      val builder = new StringBasedKeyStoreBuilder(
        """-----BEGIN CERTIFICATE-----
        |MIIDIDCCAomgAwIBAgIENd70zzANBgkqhkiG9w0BAQUFADBOMQswCQYDVQQGEwJV
        |UzEQMA4GA1UEChMHRXF1aWZheDEtMCsGA1UECxMkRXF1aWZheCBTZWN1cmUgQ2Vy
        |dGlmaWNhdGUgQXV0aG9yaXR5MB4XDTk4MDgyMjE2NDE1MVoXDTE4MDgyMjE2NDE1
        |MVowTjELMAkGA1UEBhMCVVMxEDAOBgNVBAoTB0VxdWlmYXgxLTArBgNVBAsTJEVx
        |dWlmYXggU2VjdXJlIENlcnRpZmljYXRlIEF1dGhvcml0eTCBnzANBgkqhkiG9w0B
        |AQEFAAOBjQAwgYkCgYEAwV2xWGcIYu6gmi0fCG2RFGiYCh7+2gRvE4RiIcPRfM6f
        |BeC4AfBONOziipUEZKzxa1NfBbPLZ4C/QgKO/t0BCezhABRP/PvwDN1Dulsr4R+A
        |cJkVV5MW8Q+XarfCaCMczE1ZMKxRHjuvK9buY0V7xdlfUNLjUA86iOe/FP3gx7kC
        |AwEAAaOCAQkwggEFMHAGA1UdHwRpMGcwZaBjoGGkXzBdMQswCQYDVQQGEwJVUzEQ
        |MA4GA1UEChMHRXF1aWZheDEtMCsGA1UECxMkRXF1aWZheCBTZWN1cmUgQ2VydGlm
        |aWNhdGUgQXV0aG9yaXR5MQ0wCwYDVQQDEwRDUkwxMBoGA1UdEAQTMBGBDzIwMTgw
        |ODIyMTY0MTUxWjALBgNVHQ8EBAMCAQYwHwYDVR0jBBgwFoAUSOZo+SvSspXXR9gj
        |IBBPM5iQn9QwHQYDVR0OBBYEFEjmaPkr0rKV10fYIyAQTzOYkJ/UMAwGA1UdEwQF
        |MAMBAf8wGgYJKoZIhvZ9B0EABA0wCxsFVjMuMGMDAgbAMA0GCSqGSIb3DQEBBQUA
        |A4GBAFjOKer89961zgK5F7WF0bnj4JXMJTENAKaSbn+2kmOeUJXRmm/kEd5jhW6Y
        |7qj/WsjTVbJmcVfewCHrPSqnI0kBBIZCe/zuf6IWUrVnZ9NA2zsmWLIodz2uFHdh
        |1voqZiegDfqnc1zqcPGUIWVEX/r87yloqaKHee9570+sB3c4
        |-----END CERTIFICATE-----
        |-----BEGIN CERTIFICATE-----
        |MIICWjCCAcMCAgGlMA0GCSqGSIb3DQEBBAUAMHUxCzAJBgNVBAYTAlVTMRgwFgYDVQQKEw9HVEUg
        |Q29ycG9yYXRpb24xJzAlBgNVBAsTHkdURSBDeWJlclRydXN0IFNvbHV0aW9ucywgSW5jLjEjMCEG
        |A1UEAxMaR1RFIEN5YmVyVHJ1c3QgR2xvYmFsIFJvb3QwHhcNOTgwODEzMDAyOTAwWhcNMTgwODEz
        |MjM1OTAwWjB1MQswCQYDVQQGEwJVUzEYMBYGA1UEChMPR1RFIENvcnBvcmF0aW9uMScwJQYDVQQL
        |Ex5HVEUgQ3liZXJUcnVzdCBTb2x1dGlvbnMsIEluYy4xIzAhBgNVBAMTGkdURSBDeWJlclRydXN0
        |IEdsb2JhbCBSb290MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCVD6C28FCc6HrHiM3dFw4u
        |sJTQGz0O9pTAipTHBsiQl8i4ZBp6fmw8U+E3KHNgf7KXUwefU/ltWJTSr41tiGeA5u2ylc9yMcql
        |HHK6XALnZELn+aks1joNrI1CqiQBOeacPwGFVw1Yh0X404Wqk2kmhXBIgD8SFcd5tB8FLztimQID
        |AQABMA0GCSqGSIb3DQEBBAUAA4GBAG3rGwnpXtlR22ciYaQqPEh346B8pt5zohQDhT37qw4wxYMW
        |M4ETCJ57NE7fQMh017l93PR2VX2bY1QY6fDq81yx2YtCHrnAlU66+tXifPVoYb+O7AWXX1uw16OF
        |NMQkpw0PlZPvy5TYnh+dXIVtx6quTx8itc2VrbqnzPmrC3p/
        |-----END CERTIFICATE-----
        |-----BEGIN CERTIFICATE-----
        |MIIDPDCCAqWgAwIBAgIQEj3w59oqIkekOIngiu7JZzANBgkqhkiG9w0BAQUFADCB0TELMAkGA1UE
        |BhMCWkExFTATBgNVBAgTDFdlc3Rlcm4gQ2FwZTESMBAGA1UEBxMJQ2FwZSBUb3duMRowGAYDVQQK
        |ExFUaGF3dGUgQ29uc3VsdGluZzEoMCYGA1UECxMfQ2VydGlmaWNhdGlvbiBTZXJ2aWNlcyBEaXZp
        |c2lvbjEkMCIGA1UEAxMbVGhhd3RlIFBlcnNvbmFsIEZyZWVtYWlsIENBMSswKQYJKoZIhvcNAQkB
        |FhxwZXJzb25hbC1mcmVlbWFpbEB0aGF3dGUuY29tMB4XDTk2MDEwMTAwMDAwMFoXDTIxMDEwMTIz
        |NTk1OVowgdExCzAJBgNVBAYTAlpBMRUwEwYDVQQIEwxXZXN0ZXJuIENhcGUxEjAQBgNVBAcTCUNh
        |cGUgVG93bjEaMBgGA1UEChMRVGhhd3RlIENvbnN1bHRpbmcxKDAmBgNVBAsTH0NlcnRpZmljYXRp
        |b24gU2VydmljZXMgRGl2aXNpb24xJDAiBgNVBAMTG1RoYXd0ZSBQZXJzb25hbCBGcmVlbWFpbCBD
        |QTErMCkGCSqGSIb3DQEJARYccGVyc29uYWwtZnJlZW1haWxAdGhhd3RlLmNvbTCBnzANBgkqhkiG
        |9w0BAQEFAAOBjQAwgYkCgYEA1GnX1LCUZFtx6UfYDFG26nKRsIRefS0Nj3sS34UldSh0OkIsYyef
        |lXtL734Zhx2G6qPduc6WZBrCFG5ErHzmj+hND3EfQDimAKOHePb5lIZererAXnbr2RSjXW56fAyl
        |S1V/Bhkpf56aJtVquzgkCGqYx7Hao5iR/Xnb5VrEHLkCAwEAAaMTMBEwDwYDVR0TAQH/BAUwAwEB
        |/zANBgkqhkiG9w0BAQUFAAOBgQAemGDU5fJUYLA9GoFkR/dbo9lvwykLp9KpgUn2w22FFChFRAH0
        |cVyVLhQPGivRqWvBX2c9FvFyIK++FsoOMF/Jy6WTLMNnVB5yIoojdmyUHVFSbJ3E4EcC18y/8IB7
        |GG4l3GJh1qb+wR1/2bP9jVxFEFrGZWSa6yz1A0/WSGL7Lg==
        |-----END CERTIFICATE-----
        |""".stripMargin)

      val certs = builder.build()
      certs.size() must be_==(3)
    }

  }

}
