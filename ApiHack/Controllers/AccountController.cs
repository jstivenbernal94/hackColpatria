﻿using Data.Entity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using ViewModels;

namespace ApiHack.Controllers
{
    public class AccountController : ApiController
    {
        [AllowAnonymous]
        [HttpPost]
        [Route("api/Account/Login")]
        public IHttpActionResult Login(Login dataLogin)
        {
            var entity = new EntityAccount();
            var data = new ResponseLogin();
            if (dataLogin.plataforma.Equals("m"))
            {
                data = entity.DataLoginUser(dataLogin.usuario, dataLogin.contraseña);
            }
            else if (dataLogin.plataforma.Equals("w"))
            {
                data = entity.DataLoginProve(dataLogin.usuario, dataLogin.contraseña);
            }
            return Ok(data);
        }

        [AllowAnonymous]
        [HttpGet]
        [Route("api/Account/ListProdProve")]
        public IHttpActionResult ListProdProve(int prove)
        {
            var entity = new EntityAccount();
            var dataList = new List<Provee>();
            dataList = entity.DataListProdProvee(prove);

            return Ok(dataList);
        }

        [AllowAnonymous]
        [HttpPost]
        [Route("api/Account/Crowfounding")]
        public IHttpActionResult Crowfounding(Crowfounding crow)
        {
            var entity = new EntityAccount();
            string response = entity.DataCrowfounding(crow.flag, crow.idUser, crow.idProd, crow.points, crow.help);
            return Ok(response);
        }
    }
}
