using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ViewModels
{
    public class Account
    {

    }

    public class ResponseLogin
    {
        public int id { get; set; }
        public string namec { get; set; }
        public int totalPoints { get; set; }
        public int expirationPoints { get; set; }
    }

    public class Crowfounding
    {
        public int flag { get; set; }
        public int idUser { get; set; }
        public int idProd { get; set; }
        public int points { get; set; }
        public int help { get; set; }
    }
}
