import type { TodoDto } from '@/api/models/TodoDto'

export interface TodoListDto {
  id: string

  title: string

  description: string

  todos: TodoDto[]
}
