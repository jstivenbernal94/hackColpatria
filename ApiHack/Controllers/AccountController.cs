using Data.Entity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ApiHack.Controllers
{
    public class AccountController : ApiController
    {
        [AllowAnonymous]
        [HttpPost]
        [Route("api/Account/Login")]
        public IHttpActionResult Login(string user, string password)
        {
            var entity = new EntityAccount();
            var data = entity.DataLoginUser(user, password);
            return Ok(data);
        }
    }
}
