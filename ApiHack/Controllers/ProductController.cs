using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using Data.Entity;
using Logic;
using ViewModels;

namespace ApiHack.Controllers
{
    public class ProductController : ApiController
    {
        [AllowAnonymous]
        [HttpGet]
        [Route("api/Product/CargueProductExcel")]
        public string CargueProductExcel(string ruta)
        {
            EntityProduct entity = new EntityProduct();
            Archivo arc = new Archivo();
            string[][] datosArchivo = arc.LeerArchivo(ruta);
            string response = "0";
            foreach (var item in datosArchivo)
            {
                if (item != null)
                {
                    var data = new Product
                    {
                        name = item[1],
                        prov = Convert.ToInt32(item[2]),
                        status = Convert.ToInt32(item[3]),
                        value = Convert.ToInt32(item[4]),
                        points = Convert.ToInt32(item[5]),
                        stock = Convert.ToInt32(item[6]),
                    };
                    response = entity.DataInsertProduct(data.name, data.prov, data.status, data.value, data.points, data.stock);
                }
                
            }
            return response;
        }

        [AllowAnonymous]
        [HttpPost]
        [Route("api/Account/InsertProduct")]
        public IHttpActionResult InsertProduct(Product prod)
        {
            EntityProduct entity = new EntityProduct();
            var response = entity.DataInsertProduct(prod.name, prod.prov, prod.status, prod.value, prod.points, prod.stock);
            return Ok(response);
        }


        [AllowAnonymous]
        [HttpGet]
        [Route("api/Product/ListProdUser")]
        public IHttpActionResult ListProdUser(int flag)
        {
            var entity = new EntityProduct();
            var dataList = new List<ProducUser>();
            dataList = entity.DataListProd(flag);
            return Ok(dataList);
        }
    }
}
