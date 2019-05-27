using System.Net;
using Blazor.Extensions.Logging;
using Blazored.Toast;
using Blazored.Toast.Configuration;
using MasiYellow.Infrastructure;
using Microsoft.AspNetCore.Components.Builder;
using Microsoft.Extensions.DependencyInjection;

namespace MasiYellow
{
    public class Startup
    {
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddSingleton<AuthorizationManager>();
            services.AddLogging(builder => builder.AddBrowserConsole());
            services.AddBlazoredToast(options => options.Position = ToastPosition.BottomRight);
        }

        public void Configure(IComponentsApplicationBuilder app)
        {
            app.AddComponent<App>("app");
        }
    }
}
