using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using Blazor.Extensions.Logging;
using MasiYellow.Models;
using MasiYellow.Models.Enums;
using MasiYellow.Models.View;
using MasiYellow.Util;
using Microsoft.Extensions.Logging;
using Microsoft.JSInterop;

namespace MasiYellow.Infrastructure
{
    public class ApiCommunicator
    {
        private const string Base = "http://localhost:8080/api/v1";
        private const string BaseAddress = Base + "/manage";

        private readonly AuthorizationManager _authorizationManager;
        private readonly ILogger<ApiCommunicator> _logger;
        private HttpClient _httpClient;

        public ApiCommunicator(
            AuthorizationManager authorizationManager,
            ILogger<ApiCommunicator> logger)
        {
            _authorizationManager = authorizationManager;
            _logger = logger;
            _authorizationManager.AuthChanged += UpdateHttpClient;

            _httpClient = new HttpClient();
            UpdateHttpClient(null, true);
        }

        private void UpdateHttpClient(object sender, bool auth)
        {
            if (auth)
            {
                _logger.LogInformation("Setting token header.");
                _httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Auth-Token", _authorizationManager.Token);
            }
            else
            {
                if (_httpClient.DefaultRequestHeaders.Contains("Auth-Token"))
                    _httpClient.DefaultRequestHeaders.Remove("Auth-Token");
            }
        }

        public async Task<List<User>> GetAllUsers()
        {
            try
            {
                return Json.Deserialize<List<User>>(await _httpClient.GetStringAsync($"{BaseAddress}/redactors"));
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return new List<User>();
            }
        }

        public async Task<User> GetUser(long id)
        {
            try
            {
                return Json.Deserialize<List<User>>(await _httpClient.GetStringAsync($"{BaseAddress}/redactors"))
                    .First(user => user.UserId == id);
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return new User();
            }
        }

        public async Task<bool> UpdateUser(User user)
        {
            try
            {
                HttpResponseMessage response;
                if (user.Role == UserRole.Redactor)
                {
                    response = await _httpClient.PutAsync($"{BaseAddress}/redactors/{user.UserId}", null);
                }
                else
                {
                    response = await _httpClient.DeleteAsync($"{BaseAddress}/redactors/{user.UserId}");
                }

                response.EnsureSuccessStatusCode();

                return true;
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return false;
            }
        }

        public async Task<bool> DeleteUser(User user)
        {
            try
            {
                var response = await _httpClient.DeleteAsync($"{BaseAddress}/users/{user.UserId}");
                response.EnsureSuccessStatusCode();

                return true;
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return false;
            }
        }

        public async Task<List<WorkPosition>> GetAllPositions()
        {
            try
            {
                return Json.Deserialize<List<WorkPosition>>(await _httpClient.GetStringAsync($"{BaseAddress}/positions"));
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return new List<WorkPosition>();
            }
        }

        public async Task<WorkPosition> GetPosition(long id)
        {
            try
            {
                return Json
                    .Deserialize<List<WorkPosition>>(await _httpClient.GetStringAsync($"{BaseAddress}/positions"))
                    .First(position => position.PositionId == id);
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return new WorkPosition();
            }
        }

        public async Task<WorkPosition> AddPosition(WorkPosition position)
        {
            try
            {
                var response = await _httpClient.GetAsync($"{BaseAddress}/positions/create?name={position.PositionName}");
                response.EnsureSuccessStatusCode();

                return Json.Deserialize<WorkPosition>(await response.Content.ReadAsStringAsync());
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return new WorkPosition();
            }
        }

        public async Task<bool> DeletePosition(WorkPosition position)
        {
            try
            {
                var response = await _httpClient.DeleteAsync($"{BaseAddress}/positions/{position.PositionId}");
                response.EnsureSuccessStatusCode();

                return true;
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return false;
            }
        }

        public async Task<List<Test>> GetAllTests()
        {

            try
            {
                if (_authorizationManager.CurrentUserRole == UserRole.Redactor)
                    return Json.Deserialize<List<Test>>(await _httpClient.GetStringAsync($"{BaseAddress}/tests/me"));
                else
                    return Json.Deserialize<List<Test>>(await _httpClient.GetStringAsync($"{BaseAddress}/tests"));
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return new List<Test>();
            }
        }

        public async Task<bool> DeleteTest(Test test)
        {
            try
            {
                var response = await _httpClient.DeleteAsync($"{BaseAddress}/tests/id/{test.TestId}");
                response.EnsureSuccessStatusCode();

                return true;
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return false;
            }
        }

        public async Task<Test> GetTest(int id)
        {
            try
            {
                return Json.Deserialize<Test>(await _httpClient.GetStringAsync($"{BaseAddress}/tests/id/{id}"));
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return new Test();
            }
        }

        public async Task<bool> DeleteTestQuestion(Test test, Question question)
        {
            try
            {
                var response = await _httpClient.DeleteAsync($"{BaseAddress}/tests/modify/{test.TestId}/{question.QuestionId}");
                response.EnsureSuccessStatusCode();

                return true;
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return false;
            }
        }

        public async Task<bool> AddTest(Test test)
        {
            try
            {
                var response = await _httpClient.GetAsync($"{BaseAddress}/tests/add?name={test.TestName}");
                response.EnsureSuccessStatusCode();

                return true;
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return false;
            }
        }

        public async Task<bool> AddQuestion(string testId, int number, QuestionModel questionModel)
        {
            try
            {
                var response = await _httpClient.PostAsync($"{BaseAddress}/tests/modify/{testId}", new JsonContent(new
                {
                    QuestionNumber = number,
                    QuestionDesc = questionModel.Question,
                    QuestionLang = questionModel.Language,
                    QuestionType = questionModel.QuestionType.ToString().ToUpper(),
                    QuestionData = GetMetadataString(questionModel)
                }));
                response.EnsureSuccessStatusCode();

                return true;
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return false;
            }
        }

        private string GetMetadataString(QuestionModel questionModel)
        {
            switch (questionModel.QuestionType)
            {
                case QuestionType.Open:
                    return $"|";
                case QuestionType.Number:
                    return $"|";
                case QuestionType.Scale:
                    return $"{questionModel.ScaleMin}|{questionModel.ScaleMax}";
                case QuestionType.Choice:
                    return questionModel.Choices;
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }

        public async Task<List<WorkPosition>> GetPositionsWithTestsInLanguage(Language language)
        {
            try
            {
                return Json.Deserialize<List<WorkPosition>>(await _httpClient.GetStringAsync($"{BaseAddress}/positions/{language.ToString().ToUpper()}"));
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return new List<WorkPosition>();
            }
        }

        public async Task<List<Test>> GetAllTestsForPositionInLanguage(string language, string positionId)
        {
            try
            {
                var allTests =
                    Json.Deserialize<List<Test>>(await _httpClient.GetStringAsync($"{BaseAddress}/tests"));
                var allPositions = await GetAllPositions();
                var position = allPositions.First(workPosition => workPosition.PositionId == int.Parse(positionId));
                return allTests.Where(test =>
                    position.Tests.Contains(test.TestId) &&
                    test.Language.Contains(language.ToUpper())).ToList();
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return new List<Test>();
            }
        }

        public async Task<bool> AddTestSolution(List<QuestionAnswer> answers, string testId, string language, string positionId)
        {
            try
            {
                var response = await _httpClient.PostAsync($"{BaseAddress}/answer", new JsonContent(new
                {
                    testId = int.Parse(testId),
                    positionId = int.Parse(positionId),
                    language = language.ToUpper(),
                    answers = answers
                }));
                response.EnsureSuccessStatusCode();

                return true;
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return false;
            }
        }

        public async Task<bool> AssignTestToPosition(long testId, long positionId)
        {
            try
            {
                var response = await _httpClient.PutAsync($"{BaseAddress}/positions/{positionId}/{testId}", null);
                response.EnsureSuccessStatusCode();

                return true;
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return false;
            }
        }

        public async Task<bool> RemoveAssignmentTestToPosition(long testId, long positionId)
        {
            try
            {
                var response = await _httpClient.DeleteAsync($"{BaseAddress}/positions/{positionId}/{testId}");
                response.EnsureSuccessStatusCode();

                return true;
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return false;
            }
        }

        public async Task<bool> ActivatePosition(long positionId)
        {
            try
            {
                var response = await _httpClient.PutAsync($"{BaseAddress}/positions/{positionId}/activate", null);
                response.EnsureSuccessStatusCode();

                return true;
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return false;
            }
        }

        public async Task<bool> DeactivatePosition(long positionId)
        {
            try
            {
                var response = await _httpClient.PutAsync($"{BaseAddress}/positions/{positionId}/deactivate", null);
                response.EnsureSuccessStatusCode();

                return true;
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return false;
            }
        }

        public async Task<object> ImportTest(byte[] content)
        {
            try
            {

                var multipart = new MultipartFormDataContent("Imported Test")
                {
                    {new ByteArrayContent(content), "file", "Imported Test"}
                };
                var response = await _httpClient.PostAsync($"{Base}/tests/io/upload", multipart);
                response.EnsureSuccessStatusCode();

                return true;
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return false;
            }
        }

        public async Task<byte[]> ExportTestAsCsv(Test test)
        {
            try
            {
                var response = await _httpClient.GetAsync($"{Base}/tests/io/csv/{test.TestId}");
                response.EnsureSuccessStatusCode();

                return await response.Content.ReadAsByteArrayAsync();
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return new byte[]{};
            }
        }

        public async Task<byte[]> ExportTestAsPdf(Test test)
        {
            try
            {
                var response = await _httpClient.GetAsync($"{Base}/tests/io/pdf/{test.TestId}");
                response.EnsureSuccessStatusCode();

                return await response.Content.ReadAsByteArrayAsync();
            }
            catch (Exception e)
            {
                _logger.LogError(e);
                return new byte[]{};
            }
        }
    }
}
