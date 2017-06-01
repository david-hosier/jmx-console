<%@page contentType="text/html"
        import="java.util.Iterator,org.wildfly.extras.jmxconsole.control.Server,org.wildfly.extras.jmxconsole.model.DomainData" %>
<html>
<head>
    <title>JBoss Object Index</title>
    <link rel="stylesheet" href="style_master.css" type="text/css">
    <meta http-equiv="cache-control" content="no-cache"/>
</head>

<body leftmargin="10" rightmargin="10" topmargin="10">

<table width="226" cellspacing="0" cellpadding="0" border="0">
    <tr>
        <td align="center" width="226" height="105"><img src="images/wildfly_logo.gif" border="0" alt="JBoss"/></td>
    </tr>
</table>

&nbsp;

<table width="226" cellspacing="0" cellpadding="0" border="0">
    <tr>
        <td><h2>Object Name Filter</h2></td>
    </tr>
    <tr>
        <td><h3><a href="HtmlAdaptor?action=displayMBeans&filter=" target="ObjectNodeView">Remove Object Name Filter</a>
        </h3></td>
    </tr>
    <%
    	try {
        Iterator mbeans = (Iterator) Server.getDomainData("");
        int i = 0;
        while (mbeans.hasNext()) {
            DomainData domainData = (DomainData) mbeans.next();
            out.println(" <tr>");
            out.println("  <td>");
            out.println(
                    "   <li><a href=\"HtmlAdaptor?action=displayMBeans&filter=" + domainData.getDomainName() + "\" target=\"ObjectNodeView\">" + domainData.getDomainName() + "</a></li>");
            out.println("  </td>");
            out.println(" </tr>");
        }
    	} catch (Exception e) {
    		out.println("<p>" + e.getMessage() + "</p>");
    	}
    %>
</table>

</body>
</html>
