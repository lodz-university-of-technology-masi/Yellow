using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;
using MasiYellow.Models.Enums;

namespace MasiYellow.Models.View
{
    public class QuestionModel
    {
        [Required]
        [StringLength(200, ErrorMessage = "Question can have maximum 200 characters.")]
        public string Question { get; set; }
        [Required]
        public QuestionType QuestionType { get; set; }

        [Range(-1000, 1000, ErrorMessage = "Value must be between -1000 and 1000")]
        [RegularExpression(@"^-?\d+$", ErrorMessage = "Must be a number.")]
        public string ScaleMin { get; set; }

        [Range(-1000, 1000, ErrorMessage = "Value must be between -1000 and 1000")]
        [RegularExpression(@"^-?\d+$", ErrorMessage = "Must be a number.")]
        public string ScaleMax { get; set; }

        public string Choices { get; set; }

        [RegularExpression(@"(EN|PL)", ErrorMessage = "Language should be either EN or PL")]
        public string Language { get; set; }
    }
}
