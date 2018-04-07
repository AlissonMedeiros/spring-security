# spring-security

keytool -genkeypair -alias jwt -keyalg RSA -dname "CN=jwt, L=SP, S=SP, C=DE" -keypass 842334yhrwejnsdfjsdf -keystore jwt.jks -storepass 842334yhrwejnsdfjsdf

curl BAR_ZEH:BAR_ZEH123@localhost:8080/oauth/token -d grant_type=client_credentials

curl "BAR_ZEH:BAR_ZEH123@localhost:8080/oauth/token" -d "grant_type=password&username=manuel&password=robertoleal"

curl "BAR_ZEH:BAR_ZEH123@localhost:8080/oauth/token" -d "grant_type=password&username=joao_hacker123&password=p0rtug4s3mgr4c4"


