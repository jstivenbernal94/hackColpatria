﻿using SLC;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;
using ViewModels;
using Newtonsoft.Json;

namespace ProxiService
{
    public class Proxi : IService
    {
        //direccion base
        string BaseAddress = "http://localhost:55101/";


        // los metodos asincrono regresan tareas
        public async Task<T> SendPost<T, PostData>(string requestURI, PostData data)
        {
            T Result = default(T);

            //se uso y se destruye
            using (var Client = new HttpClient())
            {
                try
                {
                    requestURI = BaseAddress + requestURI;
                    Client.DefaultRequestHeaders.Accept.Clear();
                    Client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                    var JSONData = JsonConvert.SerializeObject(data);
                    HttpResponseMessage Respond = await Client.PostAsync(requestURI, new StringContent(JSONData.ToString(), Encoding.UTF8, "application/json"));

                    var ResultWebAPI = await Respond.Content.ReadAsStringAsync();
                    Result = JsonConvert.DeserializeObject<T>(ResultWebAPI);
                }
                catch (Exception)
                {
                }
            }
            return Result;
        }

        // metodo que hace get
        public async Task<T> SendGet<T>(string requestURI)
        {
            T Result = default(T);

            //se uso y se destruye
            using (var Client = new HttpClient())
            {
                try
                {
                    requestURI = BaseAddress + requestURI;
                    Client.DefaultRequestHeaders.Accept.Clear();
                    Client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                    //var JSONData = JsonConvert.SerializeObject(data);
                    //HttpResponseMessage Respond = await Client.PostAsync(requestURI, new StringContent(JSONData.ToString(), Encoding.UTF8, "aplication/json"));

                    var ResulJSON = await Client.GetStringAsync(requestURI);
                    Result = JsonConvert.DeserializeObject<T>(ResulJSON);
                }
                catch (Exception)
                {
                }
            }
            return Result;
        }

        //-----------------------------------------------------------------------------------------------------
        public async Task<string> pruebaAsync(int ID)
        {
            return await SendGet<string>($"/api/values/{ID}");
        }

        public string prueba(int ID)
        {
            string Result = "";
            Task.Run(async () => Result = await pruebaAsync(ID)).Wait();
            return Result;
        }


        //------------------------------------------------------------------------------------------------------
        public bool ActualizarProducto(Producto updateProduct)
        {
            throw new NotImplementedException();
        }

        //------------------------------------------------------------------------------------------------------
        public Producto BuscarProductoID(int ID)
        {
            throw new NotImplementedException();
        }

        public Proveedor BuscarProveedorID(int ID)
        {
            throw new NotImplementedException();
        }

        public Producto CrearProducto(Producto newProduct)
        {
            throw new NotImplementedException();
        }

        public bool EliminarProducto(int ID)
        {
            throw new NotImplementedException();
        }

        public List<Producto> FiltraProductosProveedor(int ID)
        {
            throw new NotImplementedException();
        }       
    }
}