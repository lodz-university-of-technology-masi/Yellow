using System;
using System.Collections.Generic;
using System.Net;
using System.Threading.Tasks;
using MasiYellow.Infrastructure;
using MasiYellow.Models;
using MasiYellow.Models.Enums;
using MasiYellow.Models.View;
using Microsoft.Extensions.Logging.Abstractions;
using Xunit;

namespace MasiYellow.Test
{
    public class ApiCommunicatorTest
    {
        private AuthorizationManager _auth;
        private ApiCommunicator _api;

        public ApiCommunicatorTest()
        {
            _auth = new AuthorizationManager(new NullLogger<AuthorizationManager>());
            _api = new ApiCommunicator(_auth, new NullLogger<ApiCommunicator>());
        }

        [Fact]
        public async Task TestRegister()
        {
            var result = await _auth.Register(
                username: "test",
                password: "test");
            Assert.False(result);
        }

        [Fact]
        public async Task TestSignIn()
        {
            bool? eventResult = null;
            _auth.AuthChanged += (sender, b) =>
            {
                eventResult = b;
            };
            var result = await _auth.SignIn(
                username: "test",
                password: "test");
            Assert.False(result);
            Assert.True(eventResult.HasValue);
            Assert.False(eventResult.Value);
            Assert.Null(_auth.Token);
            Assert.False(_auth.Authorized);
        }

        [Fact]
        public async Task TestGetUsers()
        {
            var result = await _api.GetAllUsers();
            Assert.Empty(result);
        }

        [Fact]
        public async Task TestGetUser()
        {
            var result = await _api.GetUser(id: 1);
            Assert.True(result.Equals(new User()));
        }

        [Fact]
        public async Task TestUpdateUser()
        {
            var result = await _api.UpdateUser(new User
            {
                Role = UserRole.Moderator,
                UserId = int.MaxValue,
                Username = Guid.Empty.ToString()
            });
            Assert.False(result);
        }

        [Fact]
        public async Task TestDeleteUser()
        {
            var result = await _api.DeleteUser(new User
            {
                Role = UserRole.Moderator,
                UserId = int.MaxValue,
                Username = Guid.Empty.ToString()
            });
            Assert.False(result);
        }

        [Fact]
        public async Task TestGetAllPositions()
        {
            var result = await _api.GetAllPositions();
            Assert.Empty(result);
        }

        [Fact]
        public async Task TestGetPosition()
        {
            var result = await _api.GetPosition(id: 1);
            Assert.True(result.Equals(new WorkPosition()));
        }

        [Fact]
        public async Task TestAddPosition()
        {
            var result = await _api.AddPosition(new WorkPosition
            {
                PositionId = 1,
                PositionName = "test",
                IsActive = true,
                Tests = new List<long>
                {
                    1,
                    2,
                    3,
                    4,
                    5,
                    6
                }
            });
            Assert.True(result.Equals(new WorkPosition()));
        }

        [Fact]
        public async Task TestDeletePosition()
        {
            var result = await _api.DeletePosition(new WorkPosition
            {
                PositionId = 1,
                PositionName = "test",
                IsActive = true,
                Tests = new List<long>
                {
                    1,
                    2,
                    3,
                    4,
                    5,
                    6
                }
            });
            Assert.False(result);
        }

        [Fact]
        public async Task TestGetAllTests()
        {
            _auth.CurrentUserRole = UserRole.Moderator;
            Assert.Equal(UserRole.Moderator, _auth.CurrentUserRole);
            var resultModerator = await _api.GetAllTests();
            _auth.CurrentUserRole = UserRole.Redactor;
            Assert.Equal(UserRole.Redactor, _auth.CurrentUserRole);
            var resultRedactor = await _api.GetAllTests();
            Assert.Empty(resultRedactor);
            Assert.Empty(resultModerator);
        }

        [Fact]
        public async Task TestDeleteTest()
        {
            var result = await _api.DeleteTest(new Models.Test
            {
                TestName = "test",
                Questions = new List<Question>
                {
                    new Question
                    {
                        Type = QuestionType.Choice,
                        Choices = new[]
                        {
                            "A", "B", "C"
                        },
                        QuestionNumber = 2,
                        QuestionId = 3,
                        MinVal = 1,
                        MaxVal = 4,
                        QuestionLang = "EN",
                        QuestionDesc = "test"
                    },
                },
            });
            Assert.False(result);
        }

        [Fact]
        public async Task TestGetTest()
        {
            var result = await _api.GetTest(id: 1);
            Assert.True(result.Equals(new Models.Test()));
        }

        [Fact]
        public async Task TestAddQuestion()
        {
            var result = await _api.AddQuestion("1",1,new QuestionModel
            {
                Choices = "test|test",
                Language = "EN",
                QuestionType = QuestionType.Scale,
                Question = "test",
                ScaleMin = "2",
                ScaleMax = "5"
            });
            Assert.False(result);
        }
    }
}
