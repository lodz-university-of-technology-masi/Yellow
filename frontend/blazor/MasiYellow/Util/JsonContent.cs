using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Microsoft.JSInterop;

namespace MasiYellow.Util
{
    public class JsonContent : StringContent
    {
        public JsonContent(object content) : base(Json.Serialize(content), Encoding.UTF8, "application/json")
        {
        }
    }
}
