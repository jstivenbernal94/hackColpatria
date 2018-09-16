using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ViewModels;

namespace Data.Entity
{
    public class EntityAccount
    {
        readonly DBPRODEntities _ctx = new DBPRODEntities();

        public ResponseLogin DataLoginUser(string user, string password)
        {
            return _ctx.Database.SqlQuery<ResponseLogin>("sp_loginUser @user, @pass", new SqlParameter("@user", user),
                new SqlParameter("@pass", password)).FirstOrDefault();
        }

        public ResponseLogin DataLoginProve(string user, string password)
        {
            return _ctx.Database.SqlQuery<ResponseLogin>("sp_loginProveedor @user, @pass", new SqlParameter("@user", user),
                new SqlParameter("@pass", password)).FirstOrDefault();
        }

        public List<Provee> DataListProdProvee(int provee)
        {
            return _ctx.Database.SqlQuery<Provee>("sp_listaProdProv @idProv", new SqlParameter("@idProv", provee))
                .ToList();
        }


    }
}
