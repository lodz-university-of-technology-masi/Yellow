using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace MasiYellow.Models
{
    public class RegisterModel
    {
        [Required]
        [StringLength(16, ErrorMessage = "Username needs to be 6-16 characters long", MinimumLength = 6)]
        public string Username { get; set; } = "testtest";

        [Required]
        [PasswordPropertyText(true)]
        [StringLength(16, ErrorMessage = "Password needs to be 6-16 characters long", MinimumLength = 6)]
        public string Password { get; set; } = "lollol";

        [Required]
        [PasswordPropertyText(true)]
        [Compare("Password", ErrorMessage = "Confirm password doesn't match, Type again !")]
        [StringLength(16, ErrorMessage = "Password needs to be 6-16 characters long", MinimumLength = 6)]
        public string PasswordRepeat { get; set; } = "lollol";
    }
}
