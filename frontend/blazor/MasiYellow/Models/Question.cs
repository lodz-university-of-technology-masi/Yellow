using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MasiYellow.Models.Enums;

namespace MasiYellow.Models
{
    public class Question
    {
        public long QuestionId { get; set; }
        public int QuestionNumber { get; set; }
        public string QuestionLang { get; set; }
        public string QuestionDesc { get; set; }
        public QuestionType Type { get; set; }

        public string[] Choices { get; set; }
        public int MinVal { get; set; }
        public int MaxVal { get; set; }

        private string QuestionType
        {
            get { return Type.ToString().ToUpper(); }
            set
            {
                if (Enum.TryParse(value, true, out QuestionType result))
                    Type = result;
            }
        }
    }
}
