using ProxiService;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Mvc;
using ViewModels;

namespace HackProject.Controllers
{
    public class HomeController : Controller
    {        
        public ActionResult Index()
        {            
            return View();
        }

        [HttpPost]
        public ActionResult Index(Login Logueo)
        {
            var proxi = new Proxi();
            var Model = proxi.CargarDatosProveedor(Logueo);
            return View("Productos", Model);
        }

        public ActionResult Productos(ResponseLogin login)
        {
            if (login.id > 0)
            {

            }
            return View();
        }

        [HttpGet]
        public ActionResult Details()
        {
            var proxi = new Proxi();
            var Model = proxi.prueba(1);
            ViewBag.prueba = Model;
            return View();
            //return View("Details", Model);
        }
    }
}