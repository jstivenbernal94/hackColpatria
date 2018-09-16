using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ViewModels
{
    public class Proveedor
    {
        public int IDProveedor { get; set; }

        public string NombreProveedor { get; set; }

        public string DireccionProveedor { get; set; }

        public Int32 TelefonoProveedor { get; set; }

        public string LogoProveedor { get; set; }

    }

    public class Provee
    {
        public int id { get; set; }
        public string namec { get; set; }
        public int val { get; set; }
        public int points { get; set; }
        public int stock { get; set; }
    }
}
