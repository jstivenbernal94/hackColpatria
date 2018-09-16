﻿using System;
using System.Collections.Generic;
using System.Text;
using ViewModels;

namespace SLC
{
    public interface IService
    {
        // prueba

        string prueba(int ID);

        // productos

        Producto CrearProducto(Producto newProduct);

        bool ActualizarProducto(Producto updateProduct);

        Producto BuscarProductoID(int ID);

        bool EliminarProducto(int ID);

        List<Producto> FiltraProductosProveedor(int ID);

        // proveedor

        Proveedor BuscarProveedorID(int ID);
    }
}