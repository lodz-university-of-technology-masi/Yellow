using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MasiYellow.Models.Enums;

namespace MasiYellow.Models
{
    public class User
    {
        public long UserId { get; set; }
        public string Username { get; set; }

        public UserRole Role { get; set; }

        private string UserRole
        {
            get => Role.ToString().ToUpper();
            set
            {
                if (Enum.TryParse(value, true, out UserRole result))
                    Role = result;
            }
        }

        public override bool Equals(object obj)
        {
            var user = obj as User;
            if (user == null)
                return false;
            return UserId == user.UserId;
        }
    }
}
