﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MasiYellow.Models
{
    public class WorkPosition
    {
        public long PositionId { get; set; }
        public string PositionName { get; set; }
        public bool IsActive { get; set; }
        public List<long> Tests { get; set; } = new List<long>();


        public override bool Equals(object obj)
        {
            var position = obj as WorkPosition;
            if (position == null)
                return false;
            return PositionId == position.PositionId;
        }
    }
}
