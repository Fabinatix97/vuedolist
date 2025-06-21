import type { TodoDto } from "@/api/models/TodoDto";
import type { TodoListDto } from "@/api/models/TodoListDto";
import { TodoPriority } from "@/api/models/TodoPriority";
import { TodoStatus } from "@/api/models/TodoStatus";

export function fetchTodoLists(): Promise<TodoListDto[]> {
  let apiUrl = 'http://localhost:8080/todo-lists';

  return fetch(apiUrl).then((response) => {
    return response.json();
  });
}

export function fetchTodoList(todoListId: string): Promise<TodoListDto> {
  let apiUrl = 'http://localhost:8080/todo-lists/' + todoListId;

  return fetch(apiUrl).then((response) => {
    return response.json();
  });
}

export function createTodoList(
  title: string,
  description?: string
): Promise<TodoListDto> {
  let apiUrl = 'http://localhost:8080/todo-lists';

  const requestBody = {
    title: title,
    description: description
  }

  return fetch(
    apiUrl,
    {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(requestBody),
    }
  ).then((response) => {
    return response.json();
  });
}

export function deleteTodoList(todoListId: string) {
  
  let apiUrl = 'http://localhost:8080/todo-lists/' + todoListId;

  return fetch(
    apiUrl,
    {
      method: "DELETE"
    }
  );
}

export function fetchTodos(todoListId: string): Promise<TodoDto[]> {
  
  let apiUrl = 'http://localhost:8080/todo-lists/' + todoListId + '/todos';

  return fetch(apiUrl).then((response) => {
    return response.json();
  });
}

export function createTodo(
  todoListId: string,
  title: string,
  description?: string,
  status?: TodoStatus,
  priority?: TodoPriority
): Promise<TodoDto> {
  let apiUrl = 'http://localhost:8080/todo-lists/' + todoListId + '/todos';

  const requestBody = {
    title: title,
    description: description,
    status: status,
    priority: priority
  }

  return fetch(
    apiUrl,
    {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(requestBody),
    }
  ).then((response) => {
    return response.json();
  });
}

export function updateTodo(
  todoListId: string,
  todoId: string,
  title?: string,
  description?: string,
  status?: TodoStatus,
  priority?: TodoPriority
): Promise<TodoDto> {
  let apiUrl = 'http://localhost:8080/todo-lists/' + todoListId + '/todos/' + todoId;

  const requestBody = {
    id: todoId,
    title: title,
    description: description,
    status: status,
    priority: priority
  }

  return fetch(
    apiUrl,
    {
      method: "PATCH",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(requestBody),
    }
  ).then((response) => {
    return response.json();
  });
}

export function deleteTodo(todoListId: string, todoId: string) {
  
  let apiUrl = 'http://localhost:8080/todo-lists/' + todoListId + '/todos/' + todoId;

  return fetch(
    apiUrl,
    {
      method: "DELETE"
    }
  );
}