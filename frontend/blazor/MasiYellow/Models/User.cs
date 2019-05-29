using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MasiYellow.Models
{
    public class User
    {
        public long Id { get; set; }
        public string Username { get; set; }
        public UserRole Role { get; set; }
    }
}
