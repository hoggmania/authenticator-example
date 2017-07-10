<#import "template.ftl" as layout>
<@layout.registrationLayout displayInfo=social.displayInfo; section>
    <#if section = "title">
        ${msg("loginTitle",(realm.displayName!''))}
    <#elseif section = "header">
        ${msg("loginTitleHtml",(realm.displayNameHtml!''))}
    <#elseif section = "form">
        <h1>WAITING</h1>
        <script>
            setTimeout(function() { location.reload(true) }, 2000);
        </script>
    </#if>
</@layout.registrationLayout>
