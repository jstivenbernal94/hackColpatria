﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ViewModels
{
    public class Producto
    {
        public int IDProducto { get; set; }

        public string NombreProducto { get; set; }
        public string Proveedor { get; set; }
        public string Estado { get; set; }
        public string PrecioProducto { get; set; }
        public string Puntos { get; set; }
        public string UnidadesStock { get; set; }
    }

    public class Product
    {
        public string name { get; set; }
        public int prov { get; set; }
        public int status { get; set; }
        public int value { get; set; }
        public int points { get; set; }
        public int stock { get; set; }
    }

    public class ProducUser
    {
        public string namec { get; set; }
        public int status_id { get; set; }
        public int val { get; set; }
        public int points { get; set; }
    }
}
