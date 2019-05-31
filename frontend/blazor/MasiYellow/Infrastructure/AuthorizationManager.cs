using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using Blazor.Extensions.Logging;
using MasiYellow.Models;
using MasiYellow.Util;
using Microsoft.Extensions.Logging;
using Microsoft.JSInterop;

namespace MasiYellow.Infrastructure
{
    public class AuthorizationManager
    {
        private const string BaseAddress = "http://localhost:8080/api/v1";

        public event EventHandler<bool> AuthChanged;

        public bool Authorized
        {
            get => _authorized;
            set
            {
                _authorized = value;
                _logger.LogInformation("Changing auth status.");
                AuthChanged?.Invoke(this, value);
            }
        }

        public string Token { get; set; }
        public UserRole CurrentUserRole { get; set; } = UserRole.Moderator;

        private HttpClient _httpClient = new HttpClient
        {
            BaseAddress = new Uri(BaseAddress)
        };

        private readonly ILogger<AuthorizationManager> _logger;
        private bool _authorized;

        public AuthorizationManager(ILogger<AuthorizationManager> logger)
        {
            _logger = logger;
        }

        public async Task<bool> SignIn(string username, string password)
        {
            try
            {
                var response = await _httpClient.PostAsync($"{BaseAddress}/login", new JsonContent(new
                {
                    username,
                    password
                }));
                response.EnsureSuccessStatusCode();

                var json = await response.Content.ReadAsStringAsync();
                _logger.LogInformation($"Received response {json}");
                var model = Json.Deserialize<LoginResponse>(json);
                if(!model.Valid)
                    throw new Exception();
                Token = model.Token;
                CurrentUserRole = model.UserRole;
                Authorized = true;
                return true;
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return false;
            }
        }

        public async Task<bool> Register(string username, string password)
        {
            try
            {
                var response = await _httpClient.PostAsync($"{BaseAddress}/register", new JsonContent(new
                {
                    username,
                    password
                }));
                response.EnsureSuccessStatusCode();
                return true;
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return false;
            }
        }

        public void SignOut()
        {
            Authorized = false;
        }
    }
}
