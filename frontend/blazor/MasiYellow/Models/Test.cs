using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MasiYellow.Models.Enums;

namespace MasiYellow.Models
{
    public class Test
    {
        public long Id { get; set; }
        public string Name { get; set; }
        public List<Question> Questions { get; set; }
    }
}
