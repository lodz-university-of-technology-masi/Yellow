using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MasiYellow.Models
{
    public class WorkPosition
    {
        public long Id { get; set; }
        public string Name { get; set; }
        public bool IsActive { get; set; }
        public List<long> ApplicableTests { get; set; } = new List<long>();
    }
}
