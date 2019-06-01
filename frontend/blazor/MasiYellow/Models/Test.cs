using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MasiYellow.Models.Enums;

namespace MasiYellow.Models
{
    public class Test
    {
        public long TestId { get; set; }
        public string TestName { get; set; }
        public List<Question> Questions { get; set; } = new List<Question>();
        public string[] Language { get; set; }

        public override bool Equals(object obj)
        {
            var test = obj as Test;
            if (test == null)
                return false;
            return TestId == test.TestId;
        }
    }
}
