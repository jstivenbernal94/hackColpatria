using ProxiService;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Mvc;

namespace HackProject.Controllers
{
    public class HomeController : Controller
    {
        string BaseAddress = "http://localhost:57035/";

        // GET: Student
        public ActionResult Index()
        {
            //T Result = default(T);



            //using (var client = new HttpClient())
            //{
            //    client.BaseAddress = new Uri("http://localhost:64189/api/");
            //    //HTTP GET
            //    var responseTask = client.GetAsync("proveedor");
            //    responseTask.Wait();

            //    var result = responseTask.Result;
            //    if (result.IsSuccessStatusCode)
            //    {
            //        var readTask = result.Content.ReadAsAsync<IList<StudentViewModel>>();
            //        readTask.Wait();

            //        students = readTask.Result;
            //    }
            //    else //web api sent error response 
            //    {
            //        //log response status here..

            //        students = Enumerable.Empty<StudentViewModel>();

            //        ModelState.AddModelError(string.Empty, "Server error. Please contact administrator.");
            //    }
            //}
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