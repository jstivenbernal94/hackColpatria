using System;
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

        public string PrecioProducto { get; set; }

        public Int32 UnidadesStock { get; set; }
    }
}
