using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MasiYellow.Models
{
    public class LoginResponse
    {
        private string _role;
        public string Token { get; set; }
        public bool Valid { get; set; }
        public UserRole UserRole { get; set; }

        private string Role
        {
            get => _role;
            set
            {
                _role = value;
                if (Enum.TryParse(value, true, out UserRole result))
                    UserRole = result;
            }
        }
    }
}
