using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using Data.Entity;
using ViewModels;

namespace ApiHack.Controllers
{
    public class ProductController : ApiController
    {
        [AllowAnonymous]
        [HttpGet]
        [Route("api/Product/Product")]
        public string product()
        {
            EntityProduct entity = new EntityProduct();
            var data = new Product
            {
                name = "prueba",
                prov = 1,
                status = 1,
                value = 1200,
                points = 6,
                stock = 3
            };

            string xml = Logic.ConvertToXml.Serialize(data);
            var response = entity.DataInsertProduct(data.name,data.prov,data.status,data.value,data.points,data.stock);
            return response;
        }
    }
}
