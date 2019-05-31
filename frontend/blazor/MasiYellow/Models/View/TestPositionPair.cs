using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MasiYellow.Models.View
{
    public class TestPositionPair
    {
        public long TestId { get; set; }
        public long PositionId { get; set; }
        public string TestName { get; set; }
        public bool IsAssigned { get; set; }
    }
}
