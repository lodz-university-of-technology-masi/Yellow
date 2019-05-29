using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace MasiYellow.Models
{
    public class LoginModel
    {
        [Required]
        [StringLength(16, ErrorMessage = "Username needs to be 6-16 characters long", MinimumLength = 6)]
        public string Username { get; set; } = "testtest";

        [Required]
        [PasswordPropertyText(true)]
        [StringLength(16, ErrorMessage = "Password needs to be 6-16 characters long", MinimumLength = 6)]
        public string Password { get; set; } = "lollol";
    }
}
