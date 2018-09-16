using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

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



    }

    public class ResponseLogin
    {
        public int id { get; set; }
        public string namec { get; set; }
    }
}
