<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
<body>
  <h2>Producto</h2>

  <xsl:for-each select="producto">
      <p>Nombre: <xsl:value-of select="nombre"/></p>
      <p>Descripción: <xsl:value-of select="descripcion"/></p>
      <p>Precio: <xsl:value-of select="precio"/> €</p>
  </xsl:for-each>

</body>
</html>
</xsl:template>
</xsl:stylesheet>