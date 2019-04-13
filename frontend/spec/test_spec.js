import {calculateLength} from '../metricsPrototype/game';

describe('calculateLength', () => {
  it('calculate length beetwen (0, 0) and our point', () => {
    expect(calculateLength(3, 4)).toBe(5);
  });
  it('calculate length beetwen latest postion (3, 4) and next point', () => {
    expect(calculateLength(6, 8)).toBe(10);
  });
});