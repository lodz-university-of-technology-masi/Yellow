using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace MasiYellow.Models.View
{
    public class WorkPositionModel
    {
        [Required]
        [StringLength(16, ErrorMessage = "Position name needs to be 6-16 characters long", MinimumLength = 6)]
        public string Name { get; set; }
    }
}
