using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Components;

namespace MasiYellow.Util
{
    public static class NavigateExtensions
    {
        public static void Navigate(this IUriHelper helper, string path)
        {
            helper.NavigateTo($"{helper.GetBaseUri()}{path}");
        }
    }
}
