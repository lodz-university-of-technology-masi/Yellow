using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MasiYellow.Models;
using MasiYellow.Models.Enums;

namespace MasiYellow.Infrastructure
{
    public class ApiCommunicator
    {
        private readonly AuthorizationManager _authorizationManager;

        public ApiCommunicator(AuthorizationManager authorizationManager)
        {
            _authorizationManager = authorizationManager;
        }

        private List<User> _users = new List<User>
        {
            new User
            {
                Id = 1,
                Username = Guid.NewGuid().ToString(),
                Role = UserRole.User
            },
            new User
            {
                Id = 2,
                Username = Guid.NewGuid().ToString(),
                Role = UserRole.Redactor
            },
            new User
            {
                Id = 3,
                Username = Guid.NewGuid().ToString(),
                Role = UserRole.Moderator
            },
        };

        private List<WorkPosition> _positions = new List<WorkPosition>
        {
            new WorkPosition
            {
                Id = 1,
                Name = "Worker1",
                ApplicableTests = new List<long>{1}
            },
            new WorkPosition
            {
                Id = 1,
                Name = "Worker2",
                ApplicableTests = new List<long>{3}
            },
        };


        private List<Test> _tests = new List<Test>
        {
            new Test
            {
                Id = 1,
                Name = "Test1",
                Questions = new List<Question>
                {
                    new Question
                    {
                        Description = "What is life?",
                        QuestionType = QuestionType.Choice
                    }
                }
            },
            new Test
            {
                Id = 2,
                Name = "Test2",
            },
            new Test
            {
                Id = 3,
                Name = "Test3",
            },
            new Test
            {
                Id = 4,
                Name = "Test4",
            },
        };

        public async Task<List<User>> GetAllUsers()
        {
            return _users.ToList();
        }

        public async Task<User> GetUser(long id)
        {
            return _users.First(user => user.Id == id);
        }

        public async Task<bool> UpdateUser(User user)
        {
            return true;
        }

        public async Task<bool> DeleteUser(User user)
        {
            return true;
        }

        public async Task<List<WorkPosition>> GetAllPositions()
        {
            return _positions.ToList();
        }

        public async Task<WorkPosition> GetPosition(long id)
        {
            return _positions.First(user => user.Id == id);
        }

        public async Task<WorkPosition> AddPosition(WorkPosition position)
        {
            _positions.Add(position);
            position.Id = _positions.Max(workPosition => workPosition.Id) + 1;
            return position;
        }

        public async Task<bool> DeletePosition(WorkPosition position)
        {
            return true;
        }

        public async Task<List<Test>> GetAllTests()
        {
            return _tests.ToList();
        }

        public async Task<bool> DeleteTest(Test test)
        {
            return true;
        }

        public async Task<Test> GetTest(int id)
        {
            return _tests.First(test => test.Id == id);
        }

        public async Task<bool> DeleteTestQuestion(Question question)
        {
            return true;
        }

        public async Task<bool> AddTest(Test test)
        {
            test.Id = _tests.Max(t => t.Id) + 1;
            _tests.Add(test);
            return true;
        }
    }
}
