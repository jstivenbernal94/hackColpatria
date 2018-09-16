using ProxiService;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.IO.Compression;
using System.Web;
using System.Web.Mvc;
using ViewModels;
using System.IO;
using Logic;

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
            Logueo.plataforma = "w";
            var Model = proxi.CargarDatosProveedor(Logueo);

            if (Model.id > 0)
            {
                return RedirectToAction("Detalles", Model);
            }
            else
            {
                return RedirectToAction("Index");
            }
        }

        public ActionResult Detalles(ResponseLogin login)
        {
            Session["logueo"] = login;
            ViewBag.datos = login;

            int ID = login.id;
            string img = login.id + ".jpg";
            ViewBag.img = img;

            var proxi = new Proxi();
            List<Provee> Productos = proxi.FiltraProductosProveedor(ID);

            return View(Productos);
        }

        public ActionResult subir(HttpPostedFileBase doc)
        {
            if (doc != null && doc.ContentLength > 0)
            {
                try
                {
                    string path = Path.Combine(Server.MapPath("~/Archivos/Proveedores/"),Path.GetFileName(doc.FileName));

                    string path2 = Path.GetFileName(doc.FileName);

                    if (System.IO.File.Exists(path))
                    {
                        System.IO.File.Delete(path);
                        doc.SaveAs(path);
                    }
                    else
                    {
                        doc.SaveAs(path);
                    }

                    Archivo arc = new Archivo();
                    string[][] datosArchivo = arc.LeerArchivo(path2);
                    
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

                            var proxi = new Proxi();
                            string respuesta = proxi.CargueExcel(data);

                             //entity.DataInsertProduct(data.name, data.prov, data.status, data.value, data.points, data.stock);
                        }

                    }

                }
                catch (Exception ex)
                {
                    ViewBag.Message = "ERROR:" + ex.Message.ToString();
                }
            }
            ResponseLogin login = (ResponseLogin)Session["logueo"];
            return RedirectToAction("Detalles", login);
        }

        // metodo de pruebas

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