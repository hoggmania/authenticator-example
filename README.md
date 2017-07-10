# authenticator-example

* Start Keycloak or RH-SSO
* Login to admin console
* Authentication
* Select Browser, click on Copy
* Remove "Username Password Form" and "OTP Form"
* Click on "Copy of Browser Forms" -> Actions -> "Add execution"
* Select "Username form"
* Do the same for "BankID Authenticator"
* Copy username.ftl and waiting.ftl to themes/base/login (or ideally to a new custom theme)
* Login to name, you'll be asked only for username, the page will be refreshed every 2 seconds. To complete the request there is a curl command printed on sysout in Keycloak/RH-SSO once this is ran the login will complete
