for $x in doc("data/tienda.xml")/Tienda/productos
where $x/precio=1500
return $x/producto
