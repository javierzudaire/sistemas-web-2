<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
<body>
  <h2>Tienda</h2>

  <xsl:for-each select="tienda">
      <p>Nombre: <xsl:value-of select="@nombre"/></p>
      <p>Dirección: <xsl:value-of select="direccion"/></p>
      <p>Teléfono: <xsl:value-of select="telefono"/></p>
  </xsl:for-each>

  <h4>Productos:</h4>
  <table border="1">
    <tr bgcolor="#9acd32">
      <th style="text-align:left">Nombre</th>
      <th style="text-align:left">Descripción</th>
      <th style="text-align:left">Precio</th>
    </tr>
    <xsl:for-each select="tienda/productos/producto">
    <tr>
      <td><xsl:value-of select="nombre"/></td>
      <td><xsl:value-of select="descripcion"/></td>
      <td><xsl:value-of select="precio"/> €</td>
    </tr>
    </xsl:for-each>
  </table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>