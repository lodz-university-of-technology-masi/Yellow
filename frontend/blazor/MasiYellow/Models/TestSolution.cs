using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MasiYellow.Models
{
    public class TestSolution
    {
        public int SolutionId { get; set; }
        public int TestId { get; set; }
        public int PositionId { get; set; }
        public int UserId { get; set; }
        public string Language { get; set; }
        public List<SolutionAnswer> Answers { get; set; }

    }

    public class SolutionAnswer
    {
        public int AnswerId { get; set; }
        public int QuestionId { get; set; }
        public string Answer { get; set; }
        public bool Score { get; set; }
    }
}
