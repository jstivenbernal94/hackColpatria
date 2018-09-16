using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ViewModels;

namespace Data.Entity
{
    public class EntityProduct
    {
        readonly DBPRODEntities _ctx = new DBPRODEntities();

        public string DataInsertProduct(string name, int prov, int status, int val, int points, int stocks)
        {
            try
            {
                return _ctx.Database.SqlQuery<string>("sp_cargueProductos2 @namec,@prov,@status,@val,@points,@stock", 
                    new SqlParameter("@namec", name)).FirstOrDefault();
            }
            catch (Exception e)
            {
                throw e;
            }
            
        }
    }
}
