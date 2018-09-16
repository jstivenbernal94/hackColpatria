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
                return _ctx.Database.SqlQuery<string>("EXEC sp_cargueProductos2 {0}, {1}, {2},{3}, {4}, {5}",
                    new object[] {name, prov, status, val, points, stocks}).FirstOrDefault();
            }
            catch (Exception e)
            {
                throw e;
            }
            
        }

        public List<ProducUser> DataListProd(int flag)
        {
            return _ctx.Database.SqlQuery<ProducUser>("sp_listaProductos @flag", new SqlParameter("@flag", flag))
                .ToList();
        }
    }
}
