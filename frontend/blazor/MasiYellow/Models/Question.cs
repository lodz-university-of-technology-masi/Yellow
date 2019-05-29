using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MasiYellow.Models.Enums;

namespace MasiYellow.Models
{
    public class Question
    {
        public long Id { get; set; }
        public int Number { get; set; }
        public string Language { get; set; }
        public string Description { get; set; }
        public QuestionType QuestionType { get; set; }
    }
}
